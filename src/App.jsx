import { useEffect, useState } from "react";
import "./App.css";
import TileInputRow from "./components/TileInputRow";
import ScoreDisplay from "./components/ScoreDisplay";

function App() {
  const [inputTiles, setInputTiles] = useState(Array(10).fill(""));

  function handleTileInputChange(newVal, selectedTileIdx) {
    const clean = newVal.slice(-1).toUpperCase();

    // allow only A–Z
    if (clean && !/^[A-Z]$/.test(clean)) return;

    setInputTiles((prev) =>
      prev.map((curTile, i) => (i === selectedTileIdx ? newVal : curTile))
    );
  }

  useEffect(() => {
    console.log("Tiles changed:", inputTiles);
  }, [inputTiles]);

  return (
    <>
      <TileInputRow tiles={inputTiles} onTileChange={handleTileInputChange} />
      <ScoreDisplay />
      <button>Reset Tiles</button>
      <button>Save Score</button>
      <button>View Top Scores</button>
    </>
  );
}

export default App;
