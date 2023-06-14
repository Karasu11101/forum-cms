package it.jdk.gestioneforum.gestioneforum;

import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ArticoloServiceTests {

//	@Test
//	@DisplayName("Un articolo verrà creato con successo")
//	public void articoloCreateSuccess() throws RepositoryException, ServiceException {
//		ModelValidation<Articolo, ServiceException> articoloValidation = new CategoriaModelValidation();
//		Sezione sezione = new Sezione("sezione test", "questa sezione è un test", null);
//		Categoria categoria = new Categoria("categoria test", "questa categoria è un test", null);
//		Articolo articolo =
//				new Articolo("test", ZonedDateTime.now(), "questo è un test", sezione, categoria);
//		ArticoloEntity articoloEntity =
//				new ArticoloEntity("test", ZonedDateTime.now(), "questo è un test", sezione, categoria);
//
//		ArticoloService articoloService = new ArticoloService(repository, articoloValidation);
//
//		given(repository.findByTitolo(articolo.getTitolo())).willReturn(Optional.empty());
//		given(repository.createArticolo(articoloEntity)).willReturn(articoloEntity);
//
//		articoloService.createArticolo(articolo);
//
//		verify(repository).createArticolo(articoloEntity);
//	}

}
