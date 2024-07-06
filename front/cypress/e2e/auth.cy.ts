describe('Test d\'inscription', () => {
  it('doit réussir l\'inscription d\'un nouvel utilisateur', () => {
    
    cy.visit('/auth/register');

    // we create a unique suffix to ensure unique usernames 
    // it enable to run the test multiple times
    const uniqueSuffix = `${Date.now()}`;
    const username = `nouvelUtilisateur${uniqueSuffix}`;
    const email = `utilisateur${uniqueSuffix}@example.com`;
    cy.get('input[name="username"]').type(username);
    cy.get('input[name="email"]').type(email);
    cy.get('input[name="password"]').type('Motdepasse123!');

    cy.get('form').submit();

    cy.url().should('include', '/auth/login'); 
  });
});

describe('Test de connexion réussie', () => {
  it('doit réussir la connexion d\'un utilisateur', () => {

    cy.visit('/auth/login');

    cy.get('input[name="login"]').type('test10');
    cy.get('input[name="password"]').type('Test10!!');

    cy.get('form').submit();

    cy.url().should('include', '/posts/feed');
  });
});

describe('Test de connexion échouée', () => {
  it('doit échouer la connexion d\'un utilisateur', () => {

    cy.visit('/auth/login');

    cy.get('input[name="login"]').type('test10');
    cy.get('input[name="password"]').type('test10'); // wrong password

    cy.get('form').submit();

    cy.get('.errorMessage').should('contain', 'Login ou mot de passe incorrect');
  
  });
});