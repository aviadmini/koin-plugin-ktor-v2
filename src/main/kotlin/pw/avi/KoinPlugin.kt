package pw.avi

import io.ktor.server.application.Application
import io.ktor.server.application.ApplicationStopping
import io.ktor.server.application.BaseApplicationPlugin
import io.ktor.util.AttributeKey
import org.koin.core.KoinApplication
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.koinApplication
import org.koin.mp.KoinPlatformTools

class Koin private constructor(val koinApp: KoinApplication) {

    class Configuration {

        var globalContext: Boolean = true

        var configureApp: KoinAppDeclaration? = null
            private set

        fun koinApp(configure: KoinAppDeclaration) {
            configureApp = configure
        }

    }

    companion object Plugin : BaseApplicationPlugin<Application, Configuration, Koin> {

        override val key: AttributeKey<Koin>
            get() = AttributeKey("Koin")

        override fun install(
            pipeline: Application,
            configure: Configuration.() -> Unit
        ): Koin {
            val config = Configuration().apply(configure)
            val configureApp =
                config.configureApp ?: throw IllegalStateException("configure function not set up; please call koinApp() in install lambda")
            val koinApp = koinApplication(configureApp)
            if (config.globalContext) {
                KoinPlatformTools.defaultContext().startKoin(koinApp)
            }
            val monitor = pipeline.environment.monitor
            monitor.subscribe(ApplicationStopping) {
                if (config.globalContext) {
                    KoinPlatformTools.defaultContext().stopKoin()
                } else {
                    koinApp.koin.close()
                }
            }
            return Koin(koinApp)
        }
    }
}