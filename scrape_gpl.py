import requests
import re

# Stable 2026 Mirror URLs for Gpick-standard palettes
URLS = {
    "X11_Standard": "https://raw.githubusercontent.com/aseprite/aseprite/master/data/extensions/software-palettes/x11.gpl",
    "Resene_Standard": "https://raw.githubusercontent.com/inkscape/inkscape/master/share/palettes/Resene.gpl",
    "GIMP_Default": "https://raw.githubusercontent.com/GNOME/gimp/master/etc/palettes/Default.gpl"
}

def rgb_to_signed_int(r, g, b):
    # Converts 0xFFRRGGBB to a signed 32-bit integer for Kotlin
    arg_int = (0xFF << 24) | (int(r) << 16) | (int(g) << 8) | int(b)
    return arg_int if arg_int < 0x80000000 else arg_int - 0x100000000

def scrape():
    color_entries = {}
    headers = {'User-Agent': 'Mozilla/5.0'}

    for palette_name, url in URLS.items():
        print(f"// Fetching {palette_name}...")
        try:
            response = requests.get(url, headers=headers, timeout=15)
            if response.status_code != 200:
                print(f"// Error 404/Fail on {palette_name}")
                continue

            lines = response.text.splitlines()
            for line in lines:
                line = line.strip()
                # Skip header lines
                if not line or any(line.startswith(x) for x in ["GIMP", "#", "Name:", "Columns:"]):
                    continue

                # GPL format: "R G B Name" (often separated by tabs or multiple spaces)
                parts = re.split(r'\s+', line, maxsplit=3)
                if len(parts) >= 4:
                    r, g, b, name = parts[0], parts[1], parts[2], parts[3]
                    if r.isdigit() and g.isdigit() and b.isdigit():
                        signed_int = rgb_to_signed_int(r, g, b)
                        # Keep the first name found for a hex value
                        if signed_int not in color_entries:
                            color_entries[signed_int] = name.strip()

            print(f"// Loaded {len(color_entries)} colors so far...")
        except Exception as e:
            print(f"// Connection error: {e}")

    if not color_entries:
        print("// ERROR: All sources failed. Check your network.")
        return

    # Output Kotlin Map
    print("\nimport kotlin.collections.Map\n")
    print("object GpickColorMap {")
    print("    val data: Map<Int, String> = mapOf(")
    for color_int, name in sorted(color_entries.items(), key=lambda x: x[1]):
        print(f'        {color_int} to "{name}",')
    print("    )")
    print("}")

if __name__ == "__main__":
    scrape()