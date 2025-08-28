import { useEffect, useRef, useState } from "react";
import "./App.css";
import TileInputRow from "./components/TileInputRow";
import ScoreDisplay from "./components/ScoreDisplay";
import TopScoreModal from "./components/TopScoreModal";

function App() {
  const [inputTiles, setInputTiles] = useState(Array(10).fill(""));
  const [score, setScore] = useState(0);
  const [topScores, setTopScores] = useState([]);
  const dialog = useRef();

  function handleResetTiles() {
    setInputTiles(Array(10).fill(""));
  }

  function handleTileInputChange(newVal, selectedTileIdx) {
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
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({ inputTiles }), // 👈 wraps tiles array into an object
      });

      if (!res.ok) throw new Error(`Server error: ${res.status}`);
      const data = await res.json();
      console.log("Response from backend:", data);
      setScore(data.score);
    } catch (err) {
      console.error("Failed to send tiles:", err);
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

      if (!res.ok) throw new Error(`Server error: ${res.status}`);
      const data = await res.json();
      console.log("Response from backend:", data);
      setTopScores(data.topScore);
    } catch (error) {
      console.error("Failed to send tiles:", err);
    }

    dialog.current.open();
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
    } catch (err) {
      alert("Word already exists in the database");
    }
  }

  useEffect(() => {
    console.log("Tiles changed:", inputTiles);
  }, [inputTiles]);

  return (
    <>
      <TopScoreModal ref={dialog} topScores={topScores} />
      <TileInputRow tiles={inputTiles} onTileChange={handleTileInputChange} />
      <ScoreDisplay score={score} />
      <button onClick={handleResetTiles}>Reset Tiles</button>
      <button onClick={saveScore}>Save Score</button>
      <button onClick={calculateAndSetScore}>Calculate Score</button>
      <button onClick={showTopScore}>View Top Scores</button>
    </>
  );
}

export default App;
