import javax.persistence.*;
import java.util.List;

public class ConsultasJPQL {

    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Persistencia");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        // Consulta para recuperar todos os carros de uma determinada marca
        Query queryCarrosPorMarca = entityManager.createQuery("SELECT c FROM Carro c WHERE c.marca.nome = :marcaNome");
        queryCarrosPorMarca.setParameter("marcaNome", "Ford");
        List<Carro> carrosFord = queryCarrosPorMarca.getResultList();

        System.out.println("Carros da marca Ford:");
        for (Carro carro : carrosFord) {
            System.out.println(carro.getModelo());
        }

        // Consulta para recuperar todos os acessórios de um determinado carro
        Query queryAcessoriosPorCarro = entityManager.createQuery("SELECT a FROM Carro c JOIN c.acessorios a WHERE c.modelo = :carroModelo");
        queryAcessoriosPorCarro.setParameter("carroModelo", "Fiesta");
        List<Acessorio> acessoriosFiesta = queryAcessoriosPorCarro.getResultList();

        System.out.println("\nAcessórios do carro Fiesta:");
        for (Acessorio acessorio : acessoriosFiesta) {
            System.out.println(acessorio.getNome());
        }

        // Consulta para recuperar a marca de um determinado acessório
        Query queryMarcaPorAcessorio = entityManager.createQuery("SELECT a.marca FROM Acessorio a WHERE a.nome = :acessorioNome");
        queryMarcaPorAcessorio.setParameter("acessorioNome", "Ar condicionado");
        Marca marcaAcessorio = (Marca) queryMarcaPorAcessorio.getSingleResult();

        System.out.println("\nMarca do acessório Ar condicionado:");
        System.out.println(marcaAcessorio.getNome());

        entityManager.close();
        entityManagerFactory.close();
    }
}
