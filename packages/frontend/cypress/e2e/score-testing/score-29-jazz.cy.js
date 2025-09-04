describe("Reset Tiles Button", () => {
  beforeEach(() => {
    cy.visit("http://localhost:5173/"); // adjust if your frontend runs on another port
  });

  it("shows score 29 when user types 'jazz'", () => {
    const word = "jazz";

    // type each letter of the word into the tiles
    [...word].forEach((letter, idx) => {
      cy.get(`input[aria-label="Tile ${idx + 1}"]`).type(letter);
    });

    // assert that the score is displayed as 29
    cy.get(".score-value").should("have.text", "29");
  });
});
