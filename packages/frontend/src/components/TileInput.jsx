import "./TileInput.css";

export default function TileInput({ onChangeInput, tile, idx }) {
  return (
    <input
      className="scrabble-tile"
      maxLength={1}
      type="text"
      inputMode="text"
      autoComplete="off"
      spellCheck={false}
      pattern="[A-Za-z]?" // optional: allows empty or a single A–Z
      aria-label={`Tile ${idx + 1}`}
      value={tile}
      onChange={(event) => onChangeInput(event.target.value, idx)}
    />
  );
}
