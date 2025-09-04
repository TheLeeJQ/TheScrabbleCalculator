describe("Reset Tiles Button", () => {
  beforeEach(() => {
    cy.visit("http://localhost:5173/"); // adjust if your frontend runs on another port
  });

  it("shows score 12 when user types 'zoo'", () => {
    const word = "zoo";

    // type each letter of the word into the tiles
    [...word].forEach((letter, idx) => {
      cy.get(`input[aria-label="Tile ${idx + 1}"]`).type(letter);
    });

    // assert that the score is displayed as 12
    cy.get(".score-value").should("have.text", "12");
  });
});
