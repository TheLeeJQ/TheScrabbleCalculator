export default function ScoreDisplay({ score = 0 }) {
  return (
    <div className="score" aria-live="polite">
      <strong>Score:</strong>{" "}
      {score == 0 ? (
        "calculating…"
      ) : (
        <span className="score-value">{score}</span>
      )}
    </div>
  );
}
