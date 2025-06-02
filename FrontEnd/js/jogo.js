document.querySelectorAll(".thumb").forEach((thumb, index) => {
  thumb.addEventListener("click", () => {
    const videos = [
      "https://www.youtube.com/embed/UAO2urG23S4",
      "https://www.youtube.com/embed/JuP47fRBsWg",
      "https://www.youtube.com/embed/UX9P7mL9h1E",
      "https://www.youtube.com/embed/Ykxy5EY4zNU",
    ];
    document.querySelector("iframe").src = videos[index];
  });
});
