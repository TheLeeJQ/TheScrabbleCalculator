export default function TileInput({ onChangeInput, tile, idx }) {
  return (
    <input
      maxLength={1}
      type="text"
      value={tile}
      onChange={(event) => onChangeInput(event.target.value, idx)}
    />
  );
}
