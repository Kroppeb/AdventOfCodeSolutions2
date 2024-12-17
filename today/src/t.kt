import java.io.File

fun pre(day: Int, p: Int): List<String> {
	val pth = "C:\\Users\\Robbe\\Projects\\AdventOfCodeSolutions2\\solutions\\resources\\auto-test-cases\\2024\\${day}\\${p}.txt"

	// Read file
	val lines = File(pth).readLines()
	if(lines.last().isEmpty())
		return lines.subList(0,lines.lastIndex)
	return lines
}