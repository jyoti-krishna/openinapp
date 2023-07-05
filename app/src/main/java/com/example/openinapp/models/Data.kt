package com.example.openinapp.models

data class Data(
    val overall_url_chart: OverallUrlChart,
    val recent_links: List<RecentLink>,
    val top_links: List<TopLink>
)