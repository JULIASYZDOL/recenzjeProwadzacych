'use client'

import { useRouter, usePathname, useSearchParams } from 'next/navigation';
import { useEffect, useState } from 'react';
import Link from 'next/link';

async function fetchOcenaJak(pathname: string, id: string | null) {
  const string_url = `${pathname}?${id}`
  const url = new URL("http://localhost:3000" + string_url)

  const prowadzacy_nazwa = decodeURIComponent(url.pathname.split('/')[4]);

  const res = await fetch(`http://localhost:8080/prowadzacy/byNazwa/${prowadzacy_nazwa}`);
  if (!res.ok) {
    throw new Error('Failed to fetch data');
  }
  const idProw = await res.json();
  console.log('Fetched idProw:', idProw);

  const res2 = await fetch(`http://localhost:8080/ocenyJakosci/srednia/${idProw}`);
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

  const res = await fetch(`http://localhost:8080/prowadzacy/byNazwa/${prowadzacy_nazwa}`);
  if (!res.ok) {
    throw new Error('Failed to fetch data');
  }
  const idProw = await res.json();
  console.log('Fetched idProw:', idProw);

  const res2 = await fetch(`http://localhost:8080/ocenyTrudnosci/srednia/${idProw}`);
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

  const res = await fetch(`http://localhost:8080/prowadzacy/byNazwa/${prowadzacy_nazwa}`);
  if (!res.ok) {
    throw new Error('Failed to fetch data');
  }
  const idProw = await res.json();
  console.log('Fetched idProw:', idProw);

  const res2 = await fetch(`http://localhost:8080/komentarze/Pseudonimy/${idProw}`);
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

  const res = await fetch(`http://localhost:8080/prowadzacy/byNazwa/${prowadzacy_nazwa}`);
  if (!res.ok) {
    throw new Error('Failed to fetch data');
  }
  const idProw = await res.json();
  console.log('Fetched idProw:', idProw);

  const res2 = await fetch(`http://localhost:8080/komentarze/Tresci/${idProw}`);
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

    return (
    <div>
      <button type="button" onClick={() => router.back()}>Wróć do listy prowadzących</button>
      <div className="container">
        <p>Prowadzący: {prowadzacy}</p>
        <p>Uczelnia: {uczelnia}</p>
        <p>Średnia ocena jakości prowadzenia zajęć: {ocenaJak}</p>
        <p>Średnia ocena trudności zaliczenia zajęć: {ocenaTru}</p>
        <div className="button-container">
          <Link href={`${pathname}/ocena?id=${id}`}>
            <button type="button">Oceń</button>
          </Link>
          <Link href={`${pathname}/komentarz?id=${id}`}>
            <button type="button">Skomentuj</button>
          </Link>
        </div>
      </div>
        <h2>Komentarze: </h2>
        <ul className="comment-list">
          {pseudonimy.map((pseudonim, index) => (
            <li key={index} className="comment-item">
              <strong>Pseudonim:</strong> {pseudonim}
              <br/>
              <strong>Komentarz:</strong> {tresci[index]}
            </li>
          ))}
        </ul>
    </div>

  );
};
