'use client'

import { useRouter, usePathname, useSearchParams } from 'next/navigation';
import { useEffect, useState } from 'react';
import { signOut } from "next-auth/react";
import Link from "next/link";


async function fetchOcenaJak(pathname: string, id: string | null) {
  const string_url = `${pathname}?${id}`
  const url = new URL("http://localhost:3000" + string_url)

  const prowadzacy_nazwa = decodeURIComponent(url.pathname.split('/')[4]);

  const res = await fetch(`http://localhost:8081/prowadzacy/byNazwa/${prowadzacy_nazwa}`);
  if (!res.ok) {
    throw new Error('Failed to fetch data');
  }
  const idProw = await res.json();
  console.log('Fetched idProw:', idProw);

  const res2 = await fetch(`http://localhost:8081/ocenyJakosci/srednia/${idProw}`);
  if (!res2.ok) {
    throw new Error('Failed to fetch data');
  }
  const ocenaJak = await res2.json();
  console.log('Fetched ocena jakości:', ocenaJak);

  return ocenaJak;
}

async function fetchOcenaTru(pathname: string, id: string | null) {
  const string_url = `${pathname}?${id}`
  const url = new URL("http://localhost:3000" + string_url)

  const prowadzacy_nazwa = decodeURIComponent(url.pathname.split('/')[4]);

  const res = await fetch(`http://localhost:8081/prowadzacy/byNazwa/${prowadzacy_nazwa}`);
  if (!res.ok) {
    throw new Error('Failed to fetch data');
  }
  const idProw = await res.json();
  console.log('Fetched idProw:', idProw);

  const res2 = await fetch(`http://localhost:8081/ocenyTrudnosci/srednia/${idProw}`);
  if (!res2.ok) {
    throw new Error('Failed to fetch data');
  }
  const ocenaTru = await res2.json();
  console.log('Fetched ocena trudności:', ocenaTru);

  return ocenaTru;
}

async function fetchPseudonimy(pathname: string, id: string | null) {
  const string_url = `${pathname}?${id}`
  const url = new URL("http://localhost:3000" + string_url)

  const prowadzacy_nazwa = decodeURIComponent(url.pathname.split('/')[4]);

  const res = await fetch(`http://localhost:8081/prowadzacy/byNazwa/${prowadzacy_nazwa}`);
  if (!res.ok) {
    throw new Error('Failed to fetch data');
  }
  const idProw = await res.json();
  console.log('Fetched idProw:', idProw);

  const res2 = await fetch(`http://localhost:8081/komentarze/Pseudonimy/${idProw}`);
  if (!res2.ok) {
    throw new Error('Failed to fetch data');
  }
  const names = await res2.json();
  console.log('Fetched names:', names);

  return names;
}

async function fetchTresc(pathname: string, id: string | null) {
  const string_url = `${pathname}?${id}`
  const url = new URL("http://localhost:3000" + string_url)

  const prowadzacy_nazwa = decodeURIComponent(url.pathname.split('/')[4]);

  const res = await fetch(`http://localhost:8081/prowadzacy/byNazwa/${prowadzacy_nazwa}`);
  if (!res.ok) {
    throw new Error('Failed to fetch data');
  }
  const idProw = await res.json();
  console.log('Fetched idProw:', idProw);

  const res2 = await fetch(`http://localhost:8081/komentarze/Tresci/${idProw}`);
  if (!res2.ok) {
    throw new Error('Failed to fetch data');
  }
  const tresc = await res2.json();
  console.log('Fetched tresc:', tresc);

  return tresc;
}

export default function Details() {
  const router = useRouter();
  const pathname = usePathname()
  const params = useSearchParams();
  const [id, setId] = useState<string | null>(null);
  const [pseudonimy, setPseudonimy] = useState([]);
  const [tresci, setTresci] = useState([]);
  const [ocenaJak, setOcenaJak] = useState([]);
  const [ocenaTru, setOcenaTru] = useState([]);

  const string_url = `${pathname}?${id}`
  const url = new URL("http://localhost:3000" + string_url)

  const uczelnia = decodeURIComponent(url.pathname.split('/')[2]);
  const prowadzacy = decodeURIComponent(url.pathname.split('/')[4]);

  useEffect(() => {
    const idFromParams = params.get('id');
    console.log(idFromParams)

    if (idFromParams) {
      setId(idFromParams);
    }
  }, []);

  useEffect(() => {
    const fetchDataAndSetNames = async () => {
      try {
        const result = await fetchPseudonimy(pathname, id);
        setPseudonimy(result);
      } catch (error) {
        console.error(error);
      }
    };

    fetchDataAndSetNames();
  }, [pathname]);

  useEffect(() => {
    const fetchDataAndSetNames2 = async () => {
      try {
        const result = await fetchTresc(pathname, id);
        setTresci(result);
      } catch (error) {
        console.error(error);
      }
    };

    fetchDataAndSetNames2();
  }, [pathname]);

  useEffect(() => {
    const fetchDataAndSetNames3 = async () => {
      try {
        const result = await fetchOcenaJak(pathname, id);
        setOcenaJak(result);
      } catch (error) {
        console.error(error);
      }
    };

    fetchDataAndSetNames3();
  }, [pathname]);

  useEffect(() => {
    const fetchDataAndSetNames4 = async () => {
      try {
        const result = await fetchOcenaTru(pathname, id);
        setOcenaTru(result);
      } catch (error) {
        console.error(error);
      }
    };

    fetchDataAndSetNames4();
  }, [pathname]);

  const handleLogout = async () => {
    await signOut({ callbackUrl: '/' });
  };

  return (
    <div>
      <header style={{ backgroundColor: '#000', padding: '10px', display: 'flex', justifyContent: 'space-between', alignItems: 'center' }}>
        <div style={{ display: 'flex', alignItems: 'center' }}>
          <Link href="/" style={{ color: '#fff', marginRight: '10px' }}>Home</Link>
          <button type="button" style={{ color: '#fff', marginRight: '10px' }} onClick={() => router.back()}>Wróć do listy prowadzących</button>
        </div>
        <div style={{ display: 'flex', alignItems: 'center' }}>
          <button type="button" style={{ color: '#fff' }} onClick={handleLogout}>Wyloguj</button>
        </div>
      </header>
      <div className="container mx-auto px-6 py-12 h-full flex justify-center items-center">
        <div className="md:w-8/12 lg:w-5/12 bg-white px-8 py-10">
          <h1><strong>Prowadzący:</strong> {prowadzacy}</h1>
          <h1><strong>Uczelnia:</strong> {uczelnia}</h1>
          <h1><strong>Średnia ocena jakości prowadzenia zajęć:</strong> {ocenaJak}</h1>
          <h1><strong>Średnia ocena trudności zaliczenia zajęć:</strong> {ocenaTru}</h1>
          <Link href={`${pathname}/ocena?id=${id}`}>
            <button
              type="submit"
              style={{ backgroundColor: `${"#3446eb"}` }}
              className="inline-block px-7 py-4 bg-blue-600 text-white font-medium text-sm leading-snug uppercase rounded shadow-md hover:bg-blue-700 hover:shadow-lg focus:bg-blue-700 focus:shadow-lg focus:outline-none focus:ring-0 active:bg-blue-800 active:shadow-lg transition duration-150 ease-in-out w-full"
            >Oceń
            </button>
          </Link>
        </div>
      </div>
      <div className="container mx-auto px-6 py-12 h-full flex justify-center items-center">
        <div className="md:w-8/12 lg:w-5/12 bg-white px-8 py-10">
          <h3><strong>Komentarze: </strong></h3>
          <ul className="comment-list">
            {pseudonimy.map((pseudonim, index) => (
              <li key={index} className="comment-item">
                <h1><strong>Pseudonim:</strong> {pseudonim}</h1>
                <h1><strong>Komentarz:</strong> {tresci[index]}</h1>
              </li>
            ))}
          </ul>
          <Link href={`${pathname}/komentarz?id=${id}`}>
            <button
              type="submit"
              style={{ backgroundColor: `${"#3446eb"}` }}
              className="inline-block px-7 py-4 bg-blue-600 text-white font-medium text-sm leading-snug uppercase rounded shadow-md hover:bg-blue-700 hover:shadow-lg focus:bg-blue-700 focus:shadow-lg focus:outline-none focus:ring-0 active:bg-blue-800 active:shadow-lg transition duration-150 ease-in-out w-full"
            >Skomentuj
            </button>
          </Link>
        </div>
      </div>
    </div>
  );
};
