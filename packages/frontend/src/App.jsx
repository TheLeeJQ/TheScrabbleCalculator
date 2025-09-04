import { useEffect, useRef, useState } from "react";
import "./App.css";
import TileInputRow from "./components/TileInputRow";
import ScoreDisplay from "./components/ScoreDisplay";
import TopScoreModal from "./components/TopScoreModal";
import "./components/Button.css";
import homeLogo from "./assets/home-image-banner.png";

function App() {
  const [inputTiles, setInputTiles] = useState(Array(10).fill(""));
  const [score, setScore] = useState(0);
  const [topScores, setTopScores] = useState([]);
  const dialog = useRef();

  function handleResetTiles() {
    setInputTiles(Array(10).fill(""));
  }

  function handleTileInputChange(newVal, selectedTileIdx) {
    console.log("handle input tile change");
    const clean = newVal.slice(-1).toUpperCase();

    // allow only A–Z
    if (clean && !/^[A-Z]$/.test(clean)) return;

    setInputTiles((prev) =>
      prev.map((curTile, i) => (i === selectedTileIdx ? newVal : curTile))
    );
  }

  async function calculateAndSetScore() {
    try {
      const res = await fetch("http://localhost:8080/api/calculateScore", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ inputTiles }),
      });

      if (!res.ok) {
        const errorText = await res.text(); // read server's error message if any
        throw new Error(`Server error ${res.status}: ${errorText}`);
      }

      const data = await res.json();

      if (typeof data?.score !== "number") {
        throw new Error("Invalid response format from backend");
      }

      setScore(data.score);
    } catch (err) {
      console.error("Failed to send tiles:", err);
      alert(`Failed to calculate score:\n${err.message}`);
    }
  }

  async function showTopScore() {
    try {
      const res = await fetch("http://localhost:8080/api/getTopTenScores", {
        method: "GET",
        headers: {
          "Content-Type": "application/json",
        },
      });

      if (!res.ok) {
        const errorText = await res.text();
        throw new Error(`Server error ${res.status}: ${errorText}`);
      }

      const data = await res.json();

      if (!Array.isArray(data?.topScore)) {
        throw new Error("Invalid response format from backend");
      }

      setTopScores(data.topScore);
      dialog.current.open();
    } catch (err) {
      console.error("Failed to get top scores:", err);
      alert(`Failed to get top scores:\n${err.message}`);
    }
  }

  async function saveScore() {
    try {
      const res = await fetch("http://localhost:8080/api/saveScore", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({ inputTiles }), // 👈 wraps tiles array into an object
      });

      if (!res.ok) throw new Error(`Server error: ${res.status}`);
      const data = await res.json();
      console.log("Score saved:", data);
      alert("Score saved successfully!");
    } catch (err) {
      alert("Word already exists in the database");
    }
  }

  useEffect(() => {
    console.log("Tiles changed:", inputTiles);
    calculateAndSetScore();
  }, [inputTiles]);

  return (
    <>
      <img
        src={homeLogo}
        alt="Scrabble Point Calculator logo"
        className="app-logo"
      />
      <TopScoreModal ref={dialog} topScores={topScores} />
      <TileInputRow tiles={inputTiles} onTileChange={handleTileInputChange} />
      <p></p>
      <ScoreDisplay score={score} />
      <p></p>
      <button className="app-button" onClick={handleResetTiles}>
        Reset Tiles
      </button>
      <button className="app-button" onClick={saveScore}>
        Save Score
      </button>
      <button className="app-button" onClick={showTopScore}>
        View Top Scores
      </button>
    </>
  );
}

export default App;
