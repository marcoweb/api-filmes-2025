package application.filmes;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import application.generos.Genero;
import application.generos.GeneroService;

@Service
public class FilmeService {
    @Autowired
    private FilmeRepository filmeRepo;
    @Autowired
    private GeneroService generoService;

    public Iterable<FilmeDTO> getAll() {
        return filmeRepo.findAll().stream().map(FilmeDTO::new).toList();
    }

    public FilmeDTO insert(FilmeInsertDTO novoFilme) {
        Genero genero = new Genero(generoService.getOne(novoFilme.idGenero()));
        
        Filme filme = new Filme();
        filme.setTitulo(novoFilme.titulo());
        filme.setGenero(genero);

        return new FilmeDTO(filmeRepo.save(filme));
    }

    public FilmeDTO getOne(long id) {
        Optional<Filme> resultado = filmeRepo.findById(id);

        if(resultado.isEmpty()) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Filme não encontrado"
            );
        }

        return new FilmeDTO(resultado.get());
    }

    public FilmeDTO update(long id, FilmeInsertDTO novosDados) {
        Optional<Filme> resultado = filmeRepo.findById(id);

        if(resultado.isEmpty()) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Filme não encontrado"
            );
        }

        Genero genero = new Genero(generoService.getOne(novosDados.idGenero()));

        resultado.get().setTitulo(novosDados.titulo());
        resultado.get().setGenero(genero);

        return new FilmeDTO(filmeRepo.save(resultado.get()));
    }

    public void delete(long id) {
        if(!filmeRepo.existsById(id)) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Filme não encontrado"
            );
        }

        filmeRepo.deleteById(id);
    }
}
