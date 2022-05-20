import * as React from "react";
import Pagination from "@mui/material/Pagination";

export default function PaginationComponent(props) {
  const { setPage, numberPages, filmsNumber } = props;

  function handleChange(page) {
    setPage(page - 1);
    window.scrollTo(0, 0);
  }

  return (
    <div className="flex justify-center mb-5">
      <Pagination
      onChange={(e) => handleChange(e.target.textContent)}
        hideNextButton={true}
        hidePrevButton={true}
        count={numberPages}
        shape="rounded"
        size="large"
      />
    </div>
  );
}


