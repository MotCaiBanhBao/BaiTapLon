package luongvany.k12tt.style

import javafx.scene.text.FontWeight
import tornadofx.*

class Style: Stylesheet(){
    companion object{
        val hallo: CssRule by cssclass()
    }

    init{
        label and hallo{
            padding = box(10.px)
            fontSize = 110.px
            fontWeight = FontWeight.BOLD
        }
    }
}