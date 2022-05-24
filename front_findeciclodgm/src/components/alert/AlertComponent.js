import * as React from "react";
import Snackbar from "@mui/material/Snackbar";

export default function AlertComponent(props) {
  const { type, open, setOpen, message } = props;

  const handleClose = (reason) => {
    if (reason === "clickaway") {
      return;
    }

    setOpen(false);
  };

  function successAlert() {
    return (
      <Snackbar open={open} autoHideDuration={4000} onClose={handleClose}>
        <div class="alert alert-success shadow-lg">
          <div>
            <svg
              xmlns="http://www.w3.org/2000/svg"
              class="stroke-current flex-shrink-0 h-6 w-6"
              fill="none"
              viewBox="0 0 24 24"
            >
              <path
                stroke-linecap="round"
                stroke-linejoin="round"
                stroke-width="2"
                d="M9 12l2 2 4-4m6 2a9 9 0 11-18 0 9 9 0 0118 0z"
              />
            </svg>
            <span>{message}</span>
          </div>
        </div>
      </Snackbar>
    );
  }

  function errorAlert() {
    return (
      <Snackbar open={open} autoHideDuration={6000} onClose={handleClose}>
        <div class="alert alert-error shadow-lg">
          <div>
            <svg
              xmlns="http://www.w3.org/2000/svg"
              class="stroke-current flex-shrink-0 h-6 w-6"
              fill="none"
              viewBox="0 0 24 24"
            >
              <path
                stroke-linecap="round"
                stroke-linejoin="round"
                stroke-width="2"
                d="M10 14l2-2m0 0l2-2m-2 2l-2-2m2 2l2 2m7-2a9 9 0 11-18 0 9 9 0 0118 0z"
              />
            </svg>
            <span>{message}</span>
          </div>
        </div>
      </Snackbar>
    );
  }
  function renderSwitch(type) {
    switch (type) {
      case "success":
        return successAlert();
      case "error":
        return errorAlert();
      default:
        return "";
    }
  }

  return renderSwitch(type);
}
