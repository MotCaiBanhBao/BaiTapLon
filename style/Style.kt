package luongvany.k12tt.style

import javafx.scene.text.FontWeight
import tornadofx.*

class Style: Stylesheet(){
    companion object{
        val login by cssclass()
        val loginWidth = 300.px
    }

    init{
        form and login{
            padding = box(25.px)
            fontSize = 20.px
            fontWeight = FontWeight.BOLD
            prefWidth = loginWidth
            backgroundColor += c("#c4c3f4")
            button{
                prefWidth = loginWidth
            }
            progressIndicator{
                prefWidth = 16.px
            }
        }
    }
}