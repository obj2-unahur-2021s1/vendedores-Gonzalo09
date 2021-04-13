package ar.edu.unahur.obj2.vendedores

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue

class VendedorTest : DescribeSpec({
  val misiones = Provincia(1300000)
  val bsas = Provincia(4000000)
  val sanIgnacio = Ciudad(misiones)

  describe("Vendedor fijo") {
    val obera = Ciudad(misiones)
    val vendedorFijo = VendedorFijo(obera)

    describe("puedeTrabajarEn") {
      it("su ciudad de origen") {
        vendedorFijo.puedeTrabajarEn(obera).shouldBeTrue()
      }
      it("otra ciudad") {
        vendedorFijo.puedeTrabajarEn(sanIgnacio).shouldBeFalse()
      }
    }
    describe("Es "){
      it("influyente"){
        vendedorFijo.esInfluyente().shouldBeFalse()
      }
    }
  }

  describe("Viajante") {
    val cordoba = Provincia(20000000)
    val villaDolores = Ciudad(cordoba)
    val viajante = Viajante(listOf(misiones))

    describe("puedeTrabajarEn") {
      it("una ciudad que pertenece a una provincia habilitada") {
        viajante.puedeTrabajarEn(sanIgnacio).shouldBeTrue()
      }
      it("una ciudad que no pertenece a una provincia habilitada") {
        viajante.puedeTrabajarEn(villaDolores).shouldBeFalse()
      }
    }
    describe("Es "){
      it("influyente"){
        viajante.esInfluyente().shouldBeFalse()
      }
    }
  }

  describe("ComercioCorresponsal"){

    val chivilcoy= Ciudad(bsas)
    val bragado= Ciudad(bsas)
    val lobos= Ciudad(bsas)
    val pergamino= Ciudad(bsas)
    val zarate= Ciudad(bsas)
    val comercioCorresponsal = ComercioCorresponsal(listOf(chivilcoy,bragado,lobos,pergamino,zarate))
    it("Es influyente"){
      comercioCorresponsal.esInfluyente().shouldBeTrue()
    }
    it("Puede trabajar"){
      comercioCorresponsal.puedeTrabajarEn(sanIgnacio).shouldBeFalse()
    }
    val santaFe = Provincia(10020)
    val cordoba = Provincia(232849)
    val entreRios = Provincia(432423)
    val rosario = Ciudad(santaFe)
    val rafaela = Ciudad(santaFe)
    val sanFrancisco = Ciudad(cordoba)
    val diamante = Ciudad(entreRios)
    val comercioCorresponsal2 = ComercioCorresponsal(listOf(rosario,rafaela,sanFrancisco,diamante))
    it("Es influyente 2"){
      comercioCorresponsal2.esInfluyente().shouldBeTrue()
    }
    val amstrong = Ciudad(santaFe)
    val comercioCorresponsal3 = ComercioCorresponsal(listOf(rosario,rafaela,amstrong,diamante))
    it("Es influyente 3"){
      comercioCorresponsal3.esInfluyente().shouldBeFalse()
    }
  }
})