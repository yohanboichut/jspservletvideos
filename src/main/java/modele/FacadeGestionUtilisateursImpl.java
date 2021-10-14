package modele;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

public class FacadeGestionUtilisateursImpl implements FacadeGestionUtilisateurs{
    private Map<String,Utilisateur> inscrits;

    private Map<String,Utilisateur> connectes;

    public FacadeGestionUtilisateursImpl() {
        inscrits = new HashMap<>();
        connectes = new HashMap<>();
    }

    @Override
    public void inscription(String pseudo, String motDePasse) throws PseudoDejaExistantException, DonneesNonValidesException {

        if (Objects.isNull(pseudo) || Objects.isNull(motDePasse) || pseudo.isBlank() || motDePasse.isBlank()) {
            throw new DonneesNonValidesException();
        }

        if (inscrits.containsKey(pseudo)) {
            throw new PseudoDejaExistantException();
        }
        Utilisateur utilisateur = new Utilisateur(pseudo,motDePasse);
        inscrits.put(pseudo,utilisateur);

    }

    @Override
    public String connexion(String pseudo, String motDePasse) throws IdentifiantsNonValidesException, UtilisateurDejaConnecteException {
        if (Objects.isNull(pseudo) || Objects.isNull(motDePasse) || pseudo.isBlank() || motDePasse.isBlank()) {
            throw new IdentifiantsNonValidesException();
        }

        if (!inscrits.containsKey(pseudo) || !inscrits.get(pseudo).checkMotDePasse(motDePasse)) {
            throw new IdentifiantsNonValidesException();
        }

        Utilisateur utilisateur = this.inscrits.get(pseudo);
        if (connectes.containsValue(utilisateur)) {
            throw new UtilisateurDejaConnecteException();
        }

        String cle = UUID.randomUUID().toString();
        connectes.put(cle,utilisateur);
        return cle;

    }

    @Override
    public void deconnexion(String cle) throws CleInexistanteException {
        if (!connectes.containsKey(cle)) {
            throw new CleInexistanteException();
        }
        this.connectes.remove(cle);

    }

    @Override
    public Utilisateur getUtilisateurParCle(String cle) throws CleInexistanteException {
        if (!connectes.containsKey(cle)) {
            throw new CleInexistanteException();
        }
        return connectes.get(cle);

    }
}
