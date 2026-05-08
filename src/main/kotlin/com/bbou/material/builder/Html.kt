package com.bbou.material.builder

import com.materialkolor.hct.Hct

const val template = """
<!DOCTYPE html><html lang="en"><head><meta charset="utf-8"><title>%TITLE%</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<style>
div#colors div{float: left; width: 64px; height: 64px; margin: 2px; text-align: center; font-size: 12px; font-family: Arial, Helvetica, sans-serif}
div#colors div span{font-weight: bold; cursor: pointer}
div#colors div span:hover{text-decoration: underline}
html{}
input{margin-left: 1em;}
</style></head>
<body>
<div>%TITLE%</div>
<br/>
<div id="colors"><table>
%COLORS%
</table></div>
<script>
function selectText(element){ if (document.selection){ var range = document.body.createTextRange(); range.moveToElementText(element); range.select(); }else if (window.getSelection){ var range = document.createRange(); range.selectNode(element); window.getSelection().addRange(range); } }
document.getElementById('colors').addEventListener('click', function(event){ if (event.target.tagName.toLowerCase() == 'span'){ event.preventDefault(); selectText(event.target); document.execCommand('copy'); }});
</script></body></html>
"""
const val colorTemplate = "<tr><td>%LABEL%</td><td><div style=\"background-color:%BACKGROUND%; color:%FOREGROUND%\"><span>%VALUE%</span></div></td></tr>"
const val colorTemplate2 =
    "<tr><td>%LABEL%</td><td><div style=\"background-color:%LBACKGROUND%; color:%LFOREGROUND%\"><span>%LVALUE%</span></div></td><td><div style=\"background-color:%DBACKGROUND%; color:%DFOREGROUND%\"><span>%DVALUE%</span></div></td></tr>"
const val contrastTemplate =
    "<tr><td>%LABELFOREGROUND%</td><td>on</td><td>%LABELBACKGROUND%</td><td>%CONTRAST%</td><td>%PASS%</td><td><div style=\"background-color:%BACKGROUND%; color:%FOREGROUND%\"><span>%VALUE%</span></div></td><td><div style=\"background-color:%INVBACKGROUND%; color:%INVFOREGROUND%\"><span>%INVVALUE%</span></div></td></tr>"

fun printHtmlColors(colors: List<String>, title: String) {
    println(toHtml(colors, title))
}

fun printHtmlColorNV(colors: List<Pair<String, String>>, title: String) {
    println(toHtml(colors, title))
}

fun printHtmlColorMap(colors: Map<String, String>, title: String) {
    println(toHtml(colors, title))
}

fun printHtmlColorMaps(lightColors: Map<String, String>, darkColors: Map<String, String>, title: String) {
    println(toHtml2(lightColors, darkColors, title))
}

fun printHtmlColorContrast(colors: Map<String, String>, title: String) {
    println(contrastsToHtml(colors, title))
}

fun printHtmlColorContrasts(lightColors: Map<String, String>, darkColors: Map<String, String>, title: String) {
    println(contrastsToHtml2(lightColors, darkColors, title))
}

fun toHtml(colors: List<String>, title: String): String {
    val map = colors.associateWith { it }
    return toHtml(map, title)
}

fun toHtml(colors: Map<String, String>, title: String): String {
    return toHtml(colors.toList(), title = title)
}

fun toHtml(colors: Collection<Pair<String, String>>, title: String): String {
    val colorsDiv = colors.joinToString(separator = "\n") {
        val label = it.first
        val back = it.second
        val fore = if (back.toColorInt().isLight()) "#000000" else "#FFFFFF"
        colorTemplate
            .replace("%LABEL%", label)
            .replace("%VALUE%", back)
            .replace("%BACKGROUND%", back)
            .replace("%FOREGROUND%", fore)
    }
    return template
        .replace("%COLORS%", colorsDiv)
        .replace("%TITLE%", title)
}

fun toHtml2(colors1: Map<String, String>, colors2: Map<String, String>, title: String): String {
    val colorsDiv = colors1.keys.joinToString(separator = "\n") {
        val lightBack = colors1[it]!!
        val darkBack = colors2[it]!!
        val lightFore = if (lightBack.toColorInt().isLight()) "#000000" else "#FFFFFF"
        val darkFore = if (lightBack.toColorInt().isLight()) "#000000" else "#FFFFFF"
        colorTemplate2
            .replace("%LABEL%", it)
            .replace("%LVALUE%", lightBack)
            .replace("%DVALUE%", darkBack)
            .replace("%LBACKGROUND%", lightBack)
            .replace("%DBACKGROUND%", darkBack)
            .replace("%LFOREGROUND%", lightFore)
            .replace("%DFOREGROUND%", darkFore)
    }
    return template
        .replace("%COLORS%", colorsDiv)
        .replace("%TITLE%", title)
}

fun contrastsToHtml(colors: Map<String, String>, title: String): String {
    val colorsDiv = contrasts
        .map { (foreKey, backKey) ->
            val back = colors[backKey]!!
            val fore = colors[foreKey]!!
            val (pass, ratio) = contrast(fore.toColorInt(), back.toColorInt())

            contrastTemplate
                .replace("%LABELFOREGROUND%", foreKey)
                .replace("%LABELBACKGROUND%", backKey)
                .replace("%CONTRAST%", ratio.ratioFormat())
                .replace("%PASS%", if (pass) "PASS" else "FAIL")
                .replace("%INVVALUE%", back)
                .replace("%INVBACKGROUND%", back)
                .replace("%INVFOREGROUND%", fore)
                .replace("%VALUE%", fore)
                .replace("%BACKGROUND%", fore)
                .replace("%FOREGROUND%", back)
        }
        .joinToString(separator = "\n")
    return template
        .replace("%COLORS%", colorsDiv)
        .replace("%TITLE%", title)
}

fun contrastsToHtml2(colors1: Map<String, String>, colors2: Map<String, String>, title: String): String {
    val colorsDiv = contrasts
        .map { (foreKey, backKey) ->
            val lightBack = colors1[backKey]!!
            val darkBack = colors2[backKey]!!
            val lightFore = colors1[foreKey]!!
            val darkFore = colors2[foreKey]!!
            colorTemplate2
                .replace("%LABEL%", "$foreKey on $backKey")
                .replace("%LVALUE%", lightBack)
                .replace("%DVALUE%", darkBack)
                .replace("%LBACKGROUND%", lightBack)
                .replace("%DBACKGROUND%", darkBack)
                .replace("%LFOREGROUND%", lightFore)
                .replace("%DFOREGROUND%", darkFore)
        }
        .joinToString(separator = "\n")
    return template
        .replace("%COLORS%", colorsDiv)
        .replace("%TITLE%", title)
}

private fun Int.isLight(): Boolean {
    return Hct.fromInt(this).tone > 50.0
}
