import requests
import re

# High-reliability mirrors for the 1,300+ Resene names and X11
URLS = {
    "Resene_Primary": "https://raw.githubusercontent.com/GNOME/gimp/master/etc/palettes/Resene.gpl",
    "Resene_Mirror": "https://raw.githubusercontent.com/itp-dwd/2017-spring/master/palettes/Resene.gpl",
    "X11_Standard": "https://raw.githubusercontent.com/swill/x11-colors/master/x11-colors.gpl"
    "X11_Standard": "https://raw.githubusercontent.com/aseprite/aseprite/master/data/extensions/software-palettes/x11.gpl",
    "Resene_Standard": "https://raw.githubusercontent.com/inkscape/inkscape/master/share/palettes/Resene.gpl",
    "GIMP_Default": "https://raw.githubusercontent.com/GNOME/gimp/master/etc/palettes/Default.gpl"}
# The massive Resene list (1,300+ colors)
    "https://raw.githubusercontent.com/inkscape/inkscape/master/share/palettes/Resene.gpl",
    # The X11 list (shades of grey, etc.)
    "https://raw.githubusercontent.com/aseprite/aseprite/master/data/extensions/software-palettes/x11.gpl",
    # SVG/Standard list
    "https://raw.githubusercontent.com/GNOME/gimp/master/etc/palettes/SVG.gpl"
 "Resene": "https://raw.githubusercontent.com/inkscape/inkscape/master/share/palettes/Resene.gpl",
     "X11": "https://raw.githubusercontent.com/aseprite/aseprite/master/data/extensions/software-palettes/x11.gpl"
   "Resene": "https://raw.githubusercontent.com/inkscape/inkscape/master/share/palettes/Resene.gpl",
       "X11": "https://raw.githubusercontent.com/aseprite/aseprite/master/data/extensions/software-palettes/x11.gpl",
       "SVG": "https://raw.githubusercontent.com/GNOME/gimp/master/etc/palettes/SVG.gpl"  }
def rgb_to_signed_int(r, g, b):
    # Standard 0xFFRRGGBB signed 32-bit Int for Kotlin
    arg_int = (0xFF << 24) | (int(r) << 16) | (int(g) << 8) | int(b)
    return arg_int if arg_int < 0x80000000 else arg_int - 0x100000000

def scrape():
    color_entries = {}
    headers = {'User-Agent': 'Mozilla/5.0'}

    for name, url in URLS.items():
        print(f"Connecting to {name}...")
        try:
            r = requests.get(url, headers=headers, timeout=10)
            if r.status_code != 200:
                print(f"!! {name} unavailable (404/Timeout)")
                continue

            lines = r.text.splitlines()
            count = 0
            for line in lines:
                line = line.strip()
                if not line or any(line.startswith(x) for x in ["GIMP", "#", "Name", "Columns"]):
                    continue

                # Split by whitespace to handle tabs or spaces
                parts = re.split(r'\s+', line, maxsplit=3)
                if len(parts) >= 4:
                    rv, gv, bv, c_name = parts[0], parts[1], parts[2], parts[3]
                    if rv.isdigit():
                        s_int = rgb_to_signed_int(rv, gv, bv)
                        if s_int not in color_entries:
                            color_entries[s_int] = c_name.strip()
                            count += 1
            print(f"Successfully loaded {count} colors.")
        except Exception as e:
            print(f"Connection error: {e}")

    if len(color_entries) < 1000:
        print(f"\nCRITICAL ERROR: Total count is only {len(color_entries)}. The Resene database was not reached.")
        return

    print(f"\nFinal Success! {len(color_entries)} colors found. Writing GpickColorMap.kt...")
    with open("GpickColorMap.kt", "w") as f:
        f.write("package your.package.name\n\n")
        f.write("object GpickColorMap {\n")
        f.write("    val data: Map<Int, String> = mapOf(\n")
        for val, c_name in sorted(color_entries.items(), key=lambda x: x[1]):
            f.write(f'        {val} to "{c_name.replace("\"", "\\\"")}",\n')
        f.write("    )\n")
        f.write("}\n")

if __name__ == "__main__":
    scrape()