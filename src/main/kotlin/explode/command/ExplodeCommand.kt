package explode.command

import explode.ExplodeMirai
import explode.Explode
import net.mamoe.mirai.console.command.CommandSender
import net.mamoe.mirai.console.command.SimpleCommand
import thirdparty.crazy_bull.CrazyBullRCalculator

object ExplodeCommand {

	init {
		CrazyBullRCalculator.init()
	}

	object RCalculator : SimpleCommand(
		ExplodeMirai, "calcR", "DyR",
		description = "/CalcR <d> <acc> 或 /CalcR <d> <perf> <good> <miss>：计算游玩R值"
	) {

		@Handler
		suspend fun CommandSender.handleAcc(d: Double, acc: Double) {
			val r = CrazyBullRCalculator.eval(d, acc).takeIf { it >= 50.0 } ?: 0.0
			this.sendMessage("本次游玩R值为：$r")
		}

		@Handler
		suspend fun CommandSender.handleRec(d: Double, perfect: Int, good: Int, miss: Int) {
			val acc = (perfect + (good.toDouble() / 2)) / (perfect + good + miss)
			handleAcc(d, acc)
		}
	}

}