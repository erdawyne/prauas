/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.a.prauas;

import java.util.ArrayList;
import java.util.List;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ERDA WYNE
 */

@RestController
@CrossOrigin
@RequestMapping("/barangg")


public class prauasController {
    
    Barang brg = new Barang(); // mendeklarasikan entitas
    BarangJpaController ctrl = new BarangJpaController(); // mendeklarasikan jpa controller
    
    List<Barang> barangList = new ArrayList<Barang>(); //mendeklarasikan variabel list baru
    
    
    @GetMapping()
    public List<Barang> viewAll(){
        try {
            return ctrl.findBarangEntities(); //menampilkan data jika data tersedia
        } catch (Exception e){
            return List.of(); // data tidak ada
        }
    }
    
    
    @PostMapping()
    public String postBarang(@RequestBody Barang barang) // get data dari barang
    {
        try{
            ctrl.create(barang);
            return "Data tersimpan";
        } catch (Exception e) {return "Data gagal";}
    }
    
    
    @GetMapping("/{id}")
    public List<Barang> viewDatabyId(@PathVariable("id")int id){
        try {
            brg = ctrl.findBarang(id); //get data from entity
            barangList.clear();        //clear data in list
            barangList.add(brg);       //fill list
            return barangList;         //show data
        } catch (Exception e) {
            return List.of();           //Data kosong
            
        }
    }
    
    
    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE) //konversi string ke JSON
    
    public String editData(@PathVariable("id") int id, @RequestBody Barang data){
        String rslt = "Data berhasil di update";
        try {
            data.setId(id);                 //insert data
            ctrl.edit(data);                //memperbarui data dalam entitas
        } catch (Exception e) {
            rslt = e.toString() + "Update Failed";
        }
        
        return rslt;
    }
    
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE) //konversi string ke JSON
    public String delete(@PathVariable("id") int id){
        String rslt = "Data berhasil dihapus";
        try {
            ctrl.destroy(id);           //menghapus data
        } catch (Exception e) {
            rslt = "Berhasil dihapus";
        }
        
        return rslt;
    }
    
}
