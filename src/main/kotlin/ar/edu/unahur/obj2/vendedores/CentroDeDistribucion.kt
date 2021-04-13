package ar.edu.unahur.obj2.vendedores

class CentroDeDistrbucion(val ciudad: Ciudad){
    val vendedores = mutableListOf<Vendedor>()

    fun agregarVendedor(vendedor: Vendedor) {
        if (vendedores.contains(vendedor)){
            throw Exception("Ya está registrado en el centro")
        }
        else{
            vendedores.add(vendedor)
        }
    }

    fun vendedorEstrella(): Vendedor? {
        return vendedores.maxBy { v -> v.puntajeCertificaciones() }
    }

    fun puedeCubrir(ciudad: Ciudad): Boolean {
        return vendedores.any { v: Vendedor -> v.puedeTrabajarEn(ciudad) }
    }

    fun vendedoresGenericos(): List<Vendedor> {
        return vendedores.filter { v -> v.otrasCertificaciones() >= 1 }
    }

    fun esRobusto(): Boolean {
        return vendedores.count { v -> v.esFirme() } >= 3
    }
}