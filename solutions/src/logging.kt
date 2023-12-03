import me.kroppeb.aoc.helpers.Loggable
import java.awt.Toolkit
import java.awt.datatransfer.Clipboard
import java.awt.datatransfer.StringSelection


private var _logIndex = 0
fun <T> T.log(meta: String = ""): T =
	also { println("%03d %03d:\t$meta\t%s".format(_logIndex / 1000, _logIndex++ % 1000, this)) }

infix fun <T> T.log(meta: Any?): T = this.log(meta.toString())