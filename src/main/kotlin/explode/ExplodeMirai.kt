package explode

import explode.command.ExplodeCommand
import explode.command.FindCommand
import explode.dataprovider.provider.mongo.MongoProvider
import net.mamoe.mirai.console.command.CommandManager.INSTANCE.register
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescription
import net.mamoe.mirai.console.plugin.jvm.KotlinPlugin
import net.mamoe.mirai.utils.info

val Explode = MongoProvider()

object ExplodeMirai : KotlinPlugin(
	JvmPluginDescription(
		id = "explode.mirai",
		name = "Explode Mirai",
		version = "0.1.0",
	) {
		author("Taskeren")
	}
) {
	override fun onEnable() {
		logger.info { "Explode Mirai Loaded" }

		ExplodeCommand.RCalculator.register()
		FindCommand.FindSet.register()
	}
}