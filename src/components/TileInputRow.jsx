import TileInput from "./TileInput";

export default function TileInputRow({ tiles, onTileChange }) {
  return (
    <div className="tile-input-row">
      {tiles.map((val, idx) => (
        <TileInput
          key={idx}
          idx={idx}
          tile={val}
          onChangeInput={onTileChange}
        />
      ))}
    </div>
  );
}
