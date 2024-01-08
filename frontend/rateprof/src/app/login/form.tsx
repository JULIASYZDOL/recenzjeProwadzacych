"use client";

import { signIn } from "next-auth/react";
import { useSearchParams, useRouter } from "next/navigation";
import { ChangeEvent, useState } from "react";

export const LoginForm = () => {
  const router = useRouter();
  const [loading, setLoading] = useState(false);
  const [formValues, setFormValues] = useState({
    login: "",
    password: "",
  });
  const [error, setError] = useState("");

  const searchParams = useSearchParams();
  const callbackUrl = searchParams.get("callbackUrl") || "/profile";

  const onSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    try {
      setLoading(true);
      setFormValues({ login: "", password: "" });

      const res = await signIn("credentials", {
        redirect: false,
        login: formValues.login,
        password: formValues.password,
        callbackUrl,
      });

      setLoading(false);

      console.log(res);
      if (!res?.error) {
        router.push(callbackUrl);
      } else {
        setError("invalid login or password");
      }
    } catch (error: any) {
      setLoading(false);
      setError(error);
    }
  };

  const handleChange = (event: ChangeEvent<HTMLInputElement>) => {
    const { name, value } = event.target;
    setFormValues({ ...formValues, [name]: value });
  };

  const input_style =
    "form-control block w-full px-4 py-5 text-sm font-normal text-gray-700 bg-white bg-clip-padding border border-solid border-gray-300 rounded transition ease-in-out m-0 focus:text-gray-700 focus:bg-white focus:border-blue-600 focus:outline-none";

  const redirectToKeycloakLogin = () => {
    window.location.href = "http://localhost:8080/realms/myrealm/protocol/openid-connect/auth" +
    "?client_id=myclient" +
    "&redirect_uri=http://localhost:3000/profile" +
    "&response_type=code" +
    "&scope=openid";
  };
    
  
    return (
    <form onSubmit={onSubmit}>
      {error && (
        <p className="text-center bg-red-300 py-4 mb-6 rounded">{error}</p>
      )}

      {/* Log In button Keycloak*/}
      <a
        className="px-7 py-2 text-white font-medium text-sm leading-snug uppercase rounded shadow-md hover:shadow-lg focus:shadow-lg focus:outline-none focus:ring-0 active:shadow-lg transition duration-150 ease-in-out w-full flex justify-center items-center mb-3"
        style={{ backgroundColor: `${loading ? "#ccc" : "#3446eb"}` }}
        onClick={redirectToKeycloakLogin}
        role="button"
      >
        <img
          className="pr-2"
          src="/keycloak.png"
          style={{ height: "2rem" }}
        />
        {loading ? "loading..." : "Kontynuuj przez Keycloak"}
      </a>
    
      {/* OR divider */}
      <div className="flex items-center my-4 before:flex-1 before:border-t before:border-gray-300 before:mt-0.5 after:flex-1 after:border-t after:border-gray-300 after:mt-0.5">
        <p className="text-center font-semibold mx-4 mb-0">OR</p>
      </div>

      {/* Sign In with Google button */}
      <a
        className="px-7 py-2 text-white font-medium text-sm leading-snug uppercase rounded shadow-md hover:shadow-lg focus:shadow-lg focus:outline-none focus:ring-0 active:shadow-lg transition duration-150 ease-in-out w-full flex justify-center items-center mb-3"
        style={{ backgroundColor: "#ffffff", color: "gray" }}
        onClick={() => signIn("google", { callbackUrl })}
        role="button"
      >
        <img
          className="pr-2"
          src="/google.png"
          style={{ height: "2rem" }}
        />
        Kontynuuj przez Google
      </a>

      {/* Sign In with GitHub button */}
      <a
        className="px-7 py-2 text-white font-medium text-sm leading-snug uppercase rounded shadow-md hover:shadow-lg focus:shadow-lg focus:outline-none focus:ring-0 active:shadow-lg transition duration-150 ease-in-out w-full flex justify-center items-center"
        style={{ backgroundColor: "#000000" }}
        onClick={() => signIn("github", { callbackUrl })}
        role="button"
      >
        <img
          className="pr-2"
          src="/github.png"
          style={{ height: "2.2rem" }}
        />
        Kontynuuj przez GitHub
      </a>
    </form>
  );
};