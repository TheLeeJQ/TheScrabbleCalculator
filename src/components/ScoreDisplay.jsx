export default function ScoreDisplay({
  score = 0,
  isLoading = false,
  error = "",
}) {
  return (
    <div className="score" aria-live="polite">
      <strong>Score:</strong>{" "}
      {isLoading ? (
        "calculating…"
      ) : (
        <span className="score-value">{score}</span>
      )}
      {error && <span className="score-error"> · {error}</span>}
    </div>
  );
}
