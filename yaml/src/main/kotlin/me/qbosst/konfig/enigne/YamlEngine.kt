package me.qbosst.konfig.enigne

import kotlinx.serialization.*
import me.qbosst.konfig.engine.SerializationEngine
import net.mamoe.yamlkt.Yaml
import net.mamoe.yamlkt.YamlElement
import net.mamoe.yamlkt.YamlNull

class YamlEngine(override val engine: Yaml = Yaml.Default) : SerializationEngine<Yaml, YamlElement> {
    override val elementNull: YamlElement = YamlNull

    override val elementSerializer: KSerializer<YamlElement> = YamlElement.serializer()

    override fun <T> encodeToString(serializer: SerializationStrategy<T>, value: T): String {
        return engine.encodeToString(serializer, value)
    }

    override fun <T> decodeFromString(deserializer: DeserializationStrategy<T>, string: String): T {
        return engine.decodeFromString(deserializer, string)
    }

    override fun <T> encodeToElement(serializer: SerializationStrategy<T>, value: T): YamlElement {
        return engine.decodeYamlFromString(engine.encodeToString(serializer, value))
    }

    override fun <T> decodeFromElement(deserializer: DeserializationStrategy<T>, element: YamlElement): T {
        return engine.decodeFromString(deserializer, element.toString())
    }
}