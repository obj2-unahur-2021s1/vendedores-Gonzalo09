package ar.edu.unahur.obj2.vendedores

import io.kotest.assertions.throwables.shouldThrowAny
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.shouldBe


class CentroDeDistribucionTest: DescribeSpec({
    val bsas = Provincia(203024)
    val moron = Ciudad(bsas)
    val hurlingham = Ciudad(bsas)

    val centroDeDistribucion = CentroDeDistrbucion(moron)

    val vendedor1 = VendedorFijo(moron)
    val vendedor2 = VendedorFijo(hurlingham)


    describe("Agregando vendedores"){
        centroDeDistribucion.agregarVendedor(vendedor1)
        it("1 vendedor"){
            centroDeDistribucion.vendedores.size.shouldBe(1)
        }
        centroDeDistribucion.agregarVendedor(vendedor2)
        it("2 vendedores"){
            centroDeDistribucion.vendedores.size.shouldBe(2)
        }
        it("Vendedor repetido"){
            shouldThrowAny {
                centroDeDistribucion.agregarVendedor(vendedor1)
            }
        }
    }
    val certificacion1 = Certificacion(true,15)
    val certificacion11 = Certificacion(true,16)
    vendedor1.agregarCertificacion(certificacion1)
    vendedor1.agregarCertificacion(certificacion11)

    val certificacion2 = Certificacion(true,20)
    val certificacion22 = Certificacion(false,15)
    vendedor2.agregarCertificacion(certificacion2)
    vendedor2.agregarCertificacion(certificacion22)

    describe("Vendedor estrella"){
        it("Vendedor 2"){
//            centroDeDistribucion.agregarVendedor(vendedor1)
//            centroDeDistribucion.agregarVendedor(vendedor2)
            vendedor1.puntajeCertificaciones().shouldBe(31)
            vendedor2.puntajeCertificaciones().shouldBe(35)
            centroDeDistribucion.vendedorEstrella().shouldBe(vendedor2)
        }
    }

    describe(("Puede cubrir")){
        val ituzaingo = Ciudad(bsas)
        it("Moron"){
            centroDeDistribucion.puedeCubrir(moron).shouldBeTrue()
        }
        it("Hurlingham"){
            centroDeDistribucion.puedeCubrir(hurlingham).shouldBeTrue()
        }
        it("Ituzaingo"){
            centroDeDistribucion.puedeCubrir(ituzaingo).shouldBeFalse()
        }
    }

    describe("Vendedores genericos"){
        it("1 vendedor generico"){
            centroDeDistribucion.vendedoresGenericos().shouldBe(listOf(vendedor2))
        }
    }

    describe("Es robusto"){
        it("Solo 2 vendedores"){
            centroDeDistribucion.esRobusto().shouldBeFalse()
        }
        val vendedor3 = VendedorFijo(hurlingham)
        val certificacion3 = Certificacion(true,39)
        vendedor3.agregarCertificacion(certificacion3)
        centroDeDistribucion.agregarVendedor(vendedor3)
        it("3 vendedores firmes"){
            centroDeDistribucion.esRobusto().shouldBeTrue()
        }
    }
})