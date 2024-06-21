describe('Test de la page de connexion', () => {
    it('devrait se connecter avec succès', () => {
      // Visitez la page de connexion
      cy.visit('/login'); // Utilisez `baseUrl` de votre `cypress.json`
  
      // Saisissez le nom d'utilisateur et le mot de passe
      cy.get('input[name="username"]').type('votre_nom_utilisateur');
      cy.get('input[name="password"]').type('votre_mot_de_passe');
  
      // Cliquez sur le bouton de soumission
      cy.get('form').submit();
  
      // Vérifiez que la connexion a réussi
      cy.url().should('include', '/posts');
    });
  });