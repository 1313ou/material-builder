package com.bbou

import com.materialkolor.hct.Hct

const val template = """
<!DOCTYPE html><html lang="en"><head><meta charset="utf-8"><title>template.html</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<style>
div#colors div{float: left; width: 64px; height: 64px; margin: 2px; text-align: center; font-size: 12px; font-family: Arial, Helvetica, sans-serif}
div#colors div span{font-weight: bold; cursor: pointer}
div#colors div span:hover{text-decoration: underline}
html{}
input{margin-left: 1em;}
</style></head>
<body>
<div id="colors"><table>
%COLORS%
</table></div>
<script>
function selectText(element){ if (document.selection){ var range = document.body.createTextRange(); range.moveToElementText(element); range.select(); }else if (window.getSelection){ var range = document.createRange(); range.selectNode(element); window.getSelection().addRange(range); } }
document.getElementById('colors').addEventListener('click', function(event){ if (event.target.tagName.toLowerCase() == 'span'){ event.preventDefault(); selectText(event.target); document.execCommand('copy'); }});
</script></body></html>
"""
const val colorTemplate = "<tr><td>%LABEL%</td><td><div style=\"background-color:%BACKGROUND%; color:%FOREGROUND%\"><span>%VALUE%</span></div></td></tr>"

fun printHtmlColors(lightColors: Map<String, String>, darkColors: Map<String, String>) {
    println(toHtml(lightColors))
    println(toHtml(darkColors))
}

private fun Int.isLight(): Boolean {
    return Hct.fromInt(this).tone > 50.0
}

fun toHtml(colors: Map<String, String>): String {
    val colorsDiv = colors.entries.joinToString(separator = "\n") {
        val label = "${it.key}:"
        val value = it.value
        val foreground = if (value.toColorInt().isLight()) "#000000" else "#FFFFFF"
        colorTemplate
            .replace("%BACKGROUND%", value)
            .replace("%FOREGROUND%", foreground)
            .replace("%LABEL%", label)
            .replace("%VALUE%", value)
    }
    return template.replace("%COLORS%", colorsDiv)
}