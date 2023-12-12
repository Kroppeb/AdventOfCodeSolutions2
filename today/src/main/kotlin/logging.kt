import me.kroppeb.aoc.helpers.Loggable
import java.awt.Toolkit
import java.awt.datatransfer.Clipboard
import java.awt.datatransfer.StringSelection

private var _logIndex = 0
fun <T> T.log(meta: String = ""): T =
	also { println("%03d %03d:\t$meta\t%s".format(_logIndex / 1000, _logIndex++ % 1000, this)) }
		.also { if (meta == "1" || meta == "2") setClipboard(this) }

infix fun <T> T.log(meta: Any?): T = this.log(meta.toString())

private fun setClipboard(data: Any?) {
	val s: String = if (data is Loggable) data.getCopyString() else data.toString()
	val selection = StringSelection(s)
	val clipboard: Clipboard = Toolkit.getDefaultToolkit().systemClipboard
	clipboard.setContents(selection, selection)
}