package application.elenco;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/elencos")
public class ElencoController {
    @Autowired
    private ElencoService elencoService;

    @PostMapping
    public ElencoDTO insert(@RequestBody ElencoInsertDTO dados) {
        return elencoService.insert(dados);
    }

    @PutMapping("/{id}")
    public ElencoDTO update(@PathVariable long id, @RequestBody ElencoInsertDTO dados) {
        return elencoService.update(id, dados);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        elencoService.delete(id);
    }

    @GetMapping
    public Iterable<ElencoDTO> getAll() {
        return elencoService.getAll();
    }
}
