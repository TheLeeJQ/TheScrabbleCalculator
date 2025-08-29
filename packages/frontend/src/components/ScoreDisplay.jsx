export default function ScoreDisplay({ score = 0 }) {
  return (
    <div className="score" aria-live="polite" style={{ color: "#3f2c22" }}>
      <strong>Score:</strong>{" "}
      {score === 0 ? "___" : <span className="score-value">{score}</span>}
    </div>
  );
}
