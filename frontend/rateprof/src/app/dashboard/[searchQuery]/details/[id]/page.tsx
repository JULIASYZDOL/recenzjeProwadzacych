'use client'

import { useRouter, usePathname, useSearchParams } from 'next/navigation';
import { useEffect, useState } from 'react';
import Link from 'next/link';

async function fetchPseudonimy(pathname: string) {
  const nazwa_prow = pathname.replace("/dashboard/Politechnika%20Warszawska/details/", "");

  const res = await fetch(`http://localhost:8080/prowadzacy/byNazwa/{nazwa_prow}`);
  if (!res.ok) {
    throw new Error('Failed to fetch data');
  }
  const idProw = await res.json();
  console.log('Fetched idProw:', idProw);

  const res2 = await fetch(`http://localhost:8080/komentarze/Pseudonimy/{idProw}`);
  if (!res2.ok) {
    throw new Error('Failed to fetch data');
  }
  const names = await res2.json();
  console.log('Fetched names:', names);

  return names;
}

async function fetchTresc(pathname: string) {
  const nazwa_prow = pathname.replace("/dashboard/Politechnika%20Warszawska/details/", "");

  const res = await fetch(`http://localhost:8080/prowadzacy/byNazwa/{nazwa_prow}`);
  if (!res.ok) {
    throw new Error('Failed to fetch data');
  }
  const idProw = await res.json();
  console.log('Fetched idProw:', idProw);

  const res2 = await fetch(`http://localhost:8080/komentarze/Tresci/{idProw}`);
  if (!res2.ok) {
    throw new Error('Failed to fetch data');
  }
  const tresc = await res2.json();
  console.log('Fetched names:', tresc);

  return tresc;
}

export default function Details() {
    const router = useRouter();
    const pathname = usePathname()
    const id = useSearchParams();
    const [pseudonimy, setPseudonimy] = useState([]);
    const [tresci, setTresci] = useState([]);

    const url = `${pathname}?${id}`
    console.log(url)

    const nazwa = pathname.replace("/dashboard/Politechnika%20Warszawska/details/", "");
    const nazwa_pln = nazwa.replace("%C5%BC", "ż");
    const nazwa_bez_spacji_20 = nazwa_pln.replaceAll("%20", " ");
    const nazwa_bez_wszystkich_spacji = nazwa_bez_spacji_20.replaceAll("+", " ");

    const nazwa_uczelni = pathname.replace("/dashboard/", "");
    const nazwa_uczelni2 = nazwa_uczelni.replace("/details/", "");
    const nazwa_uczelni3 = nazwa_uczelni2.replace(nazwa, "")
    const uczelnia_bez_spacji_20 = nazwa_uczelni3.replaceAll("%20", " ");
    const uczelnia_bez_wszystkich_spacji = uczelnia_bez_spacji_20.replaceAll("+", " ");

    useEffect(() => {
      const fetchDataAndSetNames = async () => {
        try {
          const result = await fetchPseudonimy(pathname);
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
          const result = await fetchTresc(pathname);
          setTresci(result);
        } catch (error) {
          console.error(error);
        }
      };
  
      fetchDataAndSetNames2();
    }, [pathname]);

    return (
    <div>
      <button type="button" onClick={() => router.back()}>Wróć do listy prowadzących</button>
      <div className="container">
        <p>Prowadzący: {nazwa_bez_wszystkich_spacji}</p>
        <p>Uczelnia: {uczelnia_bez_wszystkich_spacji}</p>
        <p>Średnia ocena jakości prowadzenia zajęć: </p>
        <p>Średnia ocena trudności zaliczenia zajęć: </p>
        <div className="button-container">
          <Link href={`url/ocena`}>
            <button type="button">Oceń</button>
          </Link>
          <Link href={`url/komentarz`}>
          <button type="button">Skomentuj</button>
          </Link>
        </div>
      </div>
        <h2>Komentarze: </h2>
        <ul className="comment-list">
          {pseudonimy.map((pseudonim, index) => (
            <li key={index} className="comment-item">
              <strong>Pseudonim:</strong> {pseudonim}
              <br />
              <strong>Komentarz:</strong> {pseudonim}
            </li>
          ))}
        </ul>
    </div>

  );
};
