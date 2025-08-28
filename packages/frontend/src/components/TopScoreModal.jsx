import { useImperativeHandle, useRef } from "react";

export default function TopScoreModal({ topScores, ref }) {
  const dialog = useRef();

  useImperativeHandle(ref, () => {
    return {
      open() {
        dialog.current.showModal();
      },
    };
  });

  return (
    <dialog ref={dialog}>
      <h1>Top Scores: </h1>
      {topScores &&
        topScores.map((scoreObj, idx) => (
          <h2 key={idx}>
            {scoreObj.word.toString()} — {scoreObj.score}
          </h2>
        ))}
    </dialog>
  );
}
