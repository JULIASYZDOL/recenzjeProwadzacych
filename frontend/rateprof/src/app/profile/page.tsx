import { getServerSession } from "next-auth";
import { authOptions } from "@/lib/auth";
import Link from "next/link";

export default async function Profile() {
  const session = await getServerSession(authOptions);
  const user = session?.user;

  return (
    <>
      <div className="container mx-auto px-6 py-12 h-full flex justify-center items-center">
        <div className="md:w-8/12 lg:w-5/12 bg-white px-8 py-10">
          <div style={{ textAlign: 'center' }}>
            <h3><strong>Pomyślnie zalogowano</strong></h3>
          </div>
          {!user ? (
            <p>Loading...</p>
          ) : (
            <div className="flex items-center gap-8">
              <div>
                <img
                  src={user.image ? user.image : "/images/default.png"}
                  className="max-h-36"
                  alt={`profile photo of ${user.name}`}
                />
              </div>
              <h1><strong>Nazwa: {user.name}</strong></h1>
              <h1><strong>Email: {user.email}</strong></h1>
            </div>
          )}
          <Link href="/home"
            className="px-7 py-2 text-white font-medium text-sm leading-snug uppercase rounded shadow-md hover:shadow-lg focus:shadow-lg focus:outline-none focus:ring-0 active:shadow-lg transition duration-150 ease-in-out w-full flex justify-center items-center"
            style={{ backgroundColor: "#000000" }}
            role="button">
            Przejdź do systemu!
          </Link>
        </div>
      </div>
    </>
  );
}