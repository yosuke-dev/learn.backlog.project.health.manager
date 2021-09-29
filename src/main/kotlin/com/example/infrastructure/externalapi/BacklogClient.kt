package com.example.infrastructure.externalapi

import com.nulabinc.backlog4j.*
import com.nulabinc.backlog4j.BacklogClient
import com.nulabinc.backlog4j.conf.BacklogComConfigure

class BacklogClient(spaceId: String, apiKey: String) {
    private val configure = BacklogComConfigure(spaceId).apiKey(apiKey)
    val client: BacklogClient = BacklogClientFactory(configure).newClient()
}