package luongvany.k12tt.style

import tornadofx.*

class HomeStyle : Stylesheet(){
    companion object{
        val navBar by cssclass()
        val labelTop by cssclass()
        val centerView by cssclass()

        val paddingValue = 10.px
        val color = c("#1e88cf")
         val defaultPadding = 0.px
    }
    init {
        navBar{

        }
    }
}