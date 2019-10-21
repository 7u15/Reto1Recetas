import java.lang.Exception

const val title = ":::: Bienvenido a Recipe Maker ::::"
const val menu = """Selecciona la opción deseada
1. Hacer una receta
2. Ver mis recetas
3. Salir"""
val lstIngredientes: List<String?> =
    listOf<String?>(
        "1.Agua",
        "2.Leche",
        "3.Carne",
        "4.Verduras",
        "5.Frutas",
        "6.Cereal",
        "7.Huevos",
        "8.Aceite",
        "0.Salir"
    )

fun main(args: Array<String>) {
    println(title)
    var lstRecetas: HashMap<String, ArrayList<String>> = HashMap<String, ArrayList<String>>()
    mostrarMenu(lstRecetas)
}

fun mostrarMenu(lstRecetas: HashMap<String, ArrayList<String>>) {
    println(menu.trimIndent())
    when (readLineInt()) {
        1 -> tomarreceta(lstRecetas)
        2 -> mostrarRecetas(lstRecetas)
        3 -> println("Saliendo del Sistema")
        else ->{
            println("Option Incorrecta")
            mostrarMenu(lstRecetas)
        }
    }
}

fun tomarreceta(lstRecetas: HashMap<String, ArrayList<String>>) {
    println("===Escoga los Ingredientes por su Numero \n Al terminar presione 0")
    var lstIngredientesAgregados: ArrayList<String> = ArrayList<String>()
    lstIngredientesAgregados = addIngrediente(lstIngredientesAgregados)

    println("lstIngredientesAgregados : $lstIngredientesAgregados \n Ahora Ingrese el Nombre de Su Receta")
    val nombreReceta = readLine().toString()
    println("Ingrese la Preparacion de su Receta:")
    val descripReceta = readLine().toString()
    lstIngredientesAgregados.add(0, descripReceta)

    lstRecetas.put(nombreReceta, lstIngredientesAgregados)
    println("Receta Agregada")
    mostrarMenu(lstRecetas)
}

fun addIngrediente(lst: ArrayList<String>): ArrayList<String> {
//    for (a in lstIngredientes)  println("$a")
    println("Ingredientes: $lstIngredientes")

    val ingrediente: Int = readLineInt()
    return when (ingrediente) {
        in 1..8 -> {
            lst.add(lstIngredientes[ingrediente - 1].toString())
            println("Ingrediente Agregado: ${lstIngredientes[ingrediente - 1].toString()}")
            addIngrediente(lst)
        }
        0 -> lst
        else -> {
            println("Ingrediente no encontrado,volver Intentar")
            addIngrediente(lst)
        }
    }
}

fun mostrarRecetas(lstRecetas: HashMap<String, ArrayList<String>>) {
    if(lstRecetas.size.equals(0)){
        println(":: AUN NO HAY RECETAS ::")
    }else{
//        println("TOTAL RECETAS: ${lstRecetas.size}")
        for (receta in lstRecetas) {
            println("NOMBRE RECETA: ${receta.key} \nPREPARACION: ${receta.value[0]} \n INGREDIENTES:")
            for ((index, ingrediente) in receta.value.withIndex())
                if (!index.equals(0))
                    println(ingrediente)
        }
    }
}

fun readLineInt(): Int {
    try {
        return readLine()!!.toInt()
    } catch (e: Exception) {
//        println(e)
        println("ERROR:Ingresar Una Opcion Numerica,Volver a Intentar:")
        return readLineInt()
    }
}