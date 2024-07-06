describe('FeedComponent Tests', () => {

    beforeEach(() => {
        cy.visit('/auth/login');
        cy.get('input[name="login"]').type('Test10');
        cy.get('input[name="password"]').type('Test10!!');
        cy.get('form').submit();
        cy.url().should('include', '/posts/feed'); 
      });
  
    it('doit afficher le bouton pour créer un nouvel article', () => {
      cy.get('button').contains('Créer un article').should('be.visible');
    });
  
    it('doit permettre de changer l\'ordre de tri des articles', () => {
       cy.get('.sort-container button').click();
      cy.get('mat-icon').should('have.text', 'arrow_downward');
      cy.get('.sort-container button').click();
      cy.get('mat-icon').should('have.text', 'arrow_upward');
    });
  
    it('doit afficher les articles avec les bonnes informations', () => {
      cy.get('.card-container mat-card').should('have.length.at.least', 1);
      cy.get('.card-container mat-card').first().within(() => {
        cy.get('mat-card-title').should('not.be.empty');
        cy.get('.date').should('contain', 'Date :');
        cy.get('.auteur').should('contain', 'Auteur :');
        cy.get('#text').should('not.be.empty');
      });
    });
  });