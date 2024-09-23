package com.study.techseejfrog.ui.utils

import java.net.URL
import java.util.regex.Pattern


object Utils {

    fun getEnvironment(url: String): String {
        if (url.isEmpty())
            return ""
        val u = URL(url)
        val host = u.host
        val envArr = host.split(".")
        return envArr.first()
    }

    fun getTheme(fullUrl: String): String {
        val url = fullUrl.replaceBefore("/app/", "").substring(1)
        val segments = url.split("/")
        val pattern = Pattern.compile("(app|#)\\/(\\w{2,3}\\/)?(\\d\\/)?(\\w{4,})\\/?(\\w{2,})?")
        val matchPattern = pattern.matcher(url).matches()
        val themePattern = Pattern.compile("\\w{2,}")
        if (matchPattern) {
            if (segments.size >= 5 && themePattern.matcher(segments[4])
                    .matches()
            ) return matchTheme(segments[4])
            if (segments.size == 4 && themePattern.matcher(segments[3])
                    .matches()
            ) return matchTheme(segments[3])
        }
        return "ts"
    }

    private fun matchTheme(theme: String): String {
        return if (theme == "vfg") {
            "vgr"
        } else theme
    }
}