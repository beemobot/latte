package dev.ayu.latte.kafka

import org.apache.kafka.common.header.Headers
import java.util.UUID

private const val HEADER_REQUEST_ID = "request-id"
private const val HEADER_SOURCE_CLUSTER = "source-cluster"
private const val HEADER_TARGET_CLUSTERS = "target-clusters"

data class KafkaRecordHeaders(
    val sourceCluster: Int,
    val targetClusters: Set<Int>, // Empty = No target restriction
    val requestId: String,
) {

    constructor(sourceCluster: Int) : this(
        sourceCluster,
        emptySet(),
    )

    constructor(sourceCluster: Int, targetClusters: Set<Int>) : this(
        sourceCluster,
        targetClusters,
        UUID.randomUUID().toString(),
    )

    constructor(headers: Headers) : this(
        headers.getOrDefault(HEADER_SOURCE_CLUSTER, INVALID_CLUSTER_ID.toString()).toInt(),
        headers.getOrDefault(HEADER_TARGET_CLUSTERS, "")
            .split(",")
            .filter { it.isNotEmpty() }
            .map { it.toInt() }
            .toSet(),
        headers.getOrDefault(HEADER_REQUEST_ID, ""),
    )

    fun applyTo(headers: Headers) {
        headers.add(HEADER_TARGET_CLUSTERS, targetClusters.joinToString(",").toByteArray())
        headers.add(HEADER_REQUEST_ID, requestId.toByteArray())
        headers.add(HEADER_SOURCE_CLUSTER, "$sourceCluster".toByteArray())
    }

    companion object {

        const val INVALID_CLUSTER_ID = Integer.MIN_VALUE

    }

}

private fun Headers.getOrDefault(key: String, defaultValue: String): String {
    return String(lastHeader(key)?.value() ?: defaultValue.toByteArray())
}

data class KafkaMessage<T : Any>(
    val client: KafkaClient<T>,
    val key: String,
    val value: T?,
    val headers: KafkaRecordHeaders
) {

    fun respond(data: T?) {
        client.respond(this, data)
    }

}
