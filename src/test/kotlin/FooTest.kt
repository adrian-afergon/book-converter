import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals

class FooTest {

//    data class Reference (
//        val anchor: String,
//        val text: String,
//        val link: String
//        )
//
//    data class Result (
//            val sentence: String,
//            val footNotes: List<String>
//            )

    @Test
    fun `should split anchor from link` () {
        val input = "[this book](https://codigosostenible.com)"

        val actual = convertHyperlinkToFootNote(input)

        assertEquals(
            listOf(
                "this book [^anchor1]",
                "[^anchor1]:https://codigosostenible.com"
            ),
            actual)
    }

    private fun convertHyperlinkToFootNote(input: String): List<String> {
        val anchor = "[^anchor1]"
        val splitText = input.split("](")

        return listOf(
            "${splitText.first().drop(1)} $anchor",
            "$anchor:${splitText.last().dropLast(1)}"
        )
    }

}