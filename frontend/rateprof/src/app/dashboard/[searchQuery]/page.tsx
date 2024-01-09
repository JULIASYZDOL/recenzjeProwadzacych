'use client'

import { useEffect, useState } from 'react';
import { useRouter, usePathname, useSearchParams } from 'next/navigation';
import Link from 'next/link';
import { signOut } from "next-auth/react";

async function fetchData(pathname: string) {
  const nazwa_uczelni = pathname.replace("/dashboard/", "");

  const res = await fetch(`http://localhost:8081/uczelnie/byNazwa/${nazwa_uczelni}`);
  if (!res.ok) {
    throw new Error('Failed to fetch data');
  }
  const idUczelni = await res.json();
  console.log('Fetched idUczelni:', idUczelni);

  const res2 = await fetch(`http://localhost:8081/prowadzacy/byUczelni/${idUczelni}`);
  if (!res2.ok) {
    throw new Error('Failed to fetch data');
  }
  const names = await res2.json();
  console.log('Fetched names:', names);

  return names;
}

export default function Page2() {
  const router = useRouter();
  const [names, setNames] = useState([]);
  const pathname = usePathname();
  const id = useSearchParams();
  const url = `${pathname}?${id}`;
  console.log(url);
  const nazwa_uczelni = pathname.replace("/dashboard/", "");
  const uczelnia_bez_spacji_20 = nazwa_uczelni.replaceAll("%20", " ");
  const uczelnia_bez_wszystkich_spacji = uczelnia_bez_spacji_20.replaceAll("+", " ");

  useEffect(() => {
    const fetchDataAndSetNames = async () => {
      try {
        const result = await fetchData(pathname);
        setNames(result);
      } catch (error) {
        console.error(error);
      }
    };

    fetchDataAndSetNames();
  }, [pathname]);

  const handleLogout = async () => {
    await signOut({ callbackUrl: '/' });
  };

  return (
    <div>
      <header style={{ backgroundColor: '#000', padding: '10px', display: 'flex', justifyContent: 'space-between', alignItems: 'center' }}>
        <div style={{ display: 'flex', alignItems: 'center' }}>
          <Link href="/" style={{ color: '#fff', marginRight: '10px' }}>Home</Link>
          <button type="button" style={{ color: '#fff', marginRight: '10px' }} onClick={() => router.back()}>Wróć do wyszukiwarki uczelni</button>
        </div>
        <div style={{ display: 'flex', alignItems: 'center' }}>
          <button type="button" style={{ color: '#fff' }} onClick={handleLogout}>Wyloguj</button>
        </div>
      </header>
      <div className="container mx-auto px-6 py-12 h-full flex justify-center items-center">
          <div className="md:w-8/12 lg:w-5/12 bg-white px-8 py-10">
        <h3>Lista prowadzących na uczelni <strong>{uczelnia_bez_wszystkich_spacji}</strong>: </h3>
        <ul>
          {names.map((name, index) => (
            <li key={index}>
              <Link href={`${pathname}/details/${name}`}>
              <button
              type="submit"
              style={{ backgroundColor: `${"#3446eb"}` }}
              className="inline-block px-7 py-4 bg-blue-600 text-white font-medium text-sm leading-snug uppercase rounded shadow-md hover:bg-blue-700 hover:shadow-lg focus:bg-blue-700 focus:shadow-lg focus:outline-none focus:ring-0 active:bg-blue-800 active:shadow-lg transition duration-150 ease-in-out w-full"
            >{name}
            </button>
              </Link>
            </li>
          ))}
        </ul>
      </div>
      </div>
    </div>
  );
}
