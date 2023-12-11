'use client'

import { useEffect, useState } from 'react';
import { useRouter, usePathname, useSearchParams } from 'next/navigation';
import Link from 'next/link';

async function fetchData(pathname: string) {
  const nazwa_uczelni = pathname.replace("/dashboard/", "");

  const res = await fetch(`http://localhost:8080/uczelnie/byNazwa/${nazwa_uczelni}`);
  if (!res.ok) {
    throw new Error('Failed to fetch data');
  }
  const idUczelni = await res.json();
  console.log('Fetched idUczelni:', idUczelni);

  const res2 = await fetch(`http://localhost:8080/prowadzacy/byUczelni/${idUczelni}`);
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

  return (
    <div>
      <button type="button" onClick={() => router.back()}>Wróć do wyszukiwarki uczelni</button>
      <div className="container">
        <h1>Lista prowadzących na uczelni {uczelnia_bez_wszystkich_spacji}: </h1>
        <ul>
          {names.map((name, index) => (
            <li key={index}>
              <Link href={`${pathname}/details/${name}`}>
                {name}
              </Link>
            </li>
          ))}
        </ul>
      </div>
    </div>
  );
}
