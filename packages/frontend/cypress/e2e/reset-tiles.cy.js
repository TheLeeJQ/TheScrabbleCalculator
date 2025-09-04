describe("Reset Tiles Button", () => {
  beforeEach(() => {
    cy.visit("http://localhost:5173/"); // adjust if your frontend runs on another port
  });

  it("clears all input tiles and resets the score display", () => {
    // type some letters into the first three tiles
    cy.get('input[aria-label="Tile 1"]').type("A");
    cy.get('input[aria-label="Tile 2"]').type("B");
    cy.get('input[aria-label="Tile 3"]').type("C");

    // check that values are typed in
    cy.get('input[aria-label="Tile 1"]').should("have.value", "A");
    cy.get('input[aria-label="Tile 2"]').should("have.value", "B");
    cy.get('input[aria-label="Tile 3"]').should("have.value", "C");

    // click Reset Tiles button
    cy.contains("button", "Reset Tiles").click();

    // verify all tiles are cleared
    for (let i = 1; i <= 10; i++) {
      cy.get(`input[aria-label="Tile ${i}"]`).should("have.value", "");
    }

    // verify score display is reset to ___
    cy.get(".score").should("contain.text", "Score: ___");
  });
});
