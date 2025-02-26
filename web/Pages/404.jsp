<%@ page contentType="text/html;charset=UTF-8" %>
<html lang="en">
  <%@ include file="/WEB-INF/jspf/header.jspf" %>
  <body
    class="flex flex-col select-none items-center justify-center h-screen bg-gradient-to-t from-rose-500 to-violet-900"
  >
    <div class="text-center">
      <h1 class="text-9xl font-bold text-gray-100 animate-pulse">404</h1>
      <h2 class="text-2xl font-semibold text-gray-300 mt-4">
        Oops! Page not found.
      </h2>
      <p class="text-slate-300 mt-2">
        The page you're looking for don't exist.
      </p>

      <p class=""></p>
      <a
        href="#"
        onclick="history.back(); return false;"
        class="mt-6 relative overflow-hidden group inline-block px-6 py-3 text-white group-hover:border-none rounded-xl text-lg shadow-md border-2 transition-all"
      >
        <span
          class="-z-2 bg-slate-100 absolute inset-0 duration-700 ease-out group-hover:w-full w-0"
        ></span>
        <span class="group-hover:text-slate-700 duration-200">Return</span>
      </a>
    </div>
  </body>
</html>
