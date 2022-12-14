package explode.command

import explode.Explode
import explode.ExplodeMirai
import explode.dataprovider.model.database.MongoSet
import net.mamoe.mirai.console.command.CommandSender
import net.mamoe.mirai.console.command.SimpleCommand

object FindCommand {

	object FindSet : SimpleCommand(
		ExplodeMirai, "FindSet", "Set",
		description = "/FindSet <name> 或 /FindSet #<ID>：查询曲目信息"
	) {

		@Handler
		suspend fun CommandSender.handle(searchInfo: String) {
			val sets: List<MongoSet> = if(searchInfo.startsWith("#")) {
				val id = searchInfo.substring(1)
				listOfNotNull(Explode.getSet(id))
			} else {
				Explode.getSetByName(searchInfo).toList()
			}

			when(sets.size) {
				0 -> {
					sendMessage("没有查询到匹配的曲目。")
				}
				1 -> {
					val set = sets.single()
					val message = """
						《${set.musicName}》
						${set._id}
						价格：${getPriceText(set.price)}
						作者ID：${set.noterId}
						拥有难度：${set.getChartsSummary()}
					""".trimIndent()
					sendMessage(message)
				}
				else -> {
					val setsDetails = sets.joinToString(separator = "\n", prefix = "- ") {
						"<${it._id}>：${it.getChartsSummary()}"
					}
					val message = """
						匹配到多条曲目
						$setsDetails
					""".trimIndent()
					sendMessage(message)
				}
			}
		}

		private fun getPriceText(price: Int): String = when(price) {
			0 -> "免费"
			else -> "$price 金币"
		}

		private fun getHardLevelText(level: Int): String = when(level) {
			1 -> "CASUAL"
			2 -> "NORMAL"
			3 -> "HARD"
			4 -> "MEGA"
			5 -> "GIGA"
			else -> "UNKNOWN"
		}

		private fun MongoSet.getChartsSummary(): String = with(Explode) {
			val charts = getCharts()
			return charts.joinToString(separator = "，") {
				"${getHardLevelText(it.difficultyClass)} ${it.difficultyValue}"
			}
		}

	}

}